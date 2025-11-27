import {
    AutocompleteInput,
    DateInput,
    Edit,
    FormDataConsumer,
    NumberInput,
    ReferenceInput,
    SelectInput,
    SimpleForm,
    required,
    useNotify,
} from 'react-admin';

const clienteChoices = [
    { id: 'socio', name: 'Sócio' },
    { id: 'dependente', name: 'Dependente' },
];

const sanitizePayload = (data) => {
    const { clienteTipo, ...payload } = data;
    if (clienteTipo === 'dependente') {
        delete payload.socioId;
    } else {
        delete payload.dependenteId;
    }
    return payload;
};

const buildErrorMessage = (error, fallback) => {
    if (error?.body) {
        try {
            const parsed = JSON.parse(error.body);
            if (parsed?.message) {
                let message = parsed.message;
                if (Array.isArray(parsed.erros) && parsed.erros.length > 0) {
                    message += '\n' + parsed.erros.map((e) => `${e.campo}: ${e.erro}`).join('\n');
                }
                return message;
            }
        } catch {
            /* ignore parse errors */
        }
    }
    return fallback;
};

const ClienteSelector = () => (
    <FormDataConsumer>
        {({ formData }) => {
            const tipo = formData?.clienteTipo ?? 'socio';
            const reference = tipo === 'dependente' ? 'dependentes' : 'socios';
            const label = tipo === 'dependente' ? 'Dependente' : 'Sócio';
            const source = tipo === 'dependente' ? 'dependenteId' : 'socioId';

            return (
                <ReferenceInput key={source} source={source} reference={reference} label={label} perPage={100}>
                    <AutocompleteInput optionText="nome" validate={required()} />
                </ReferenceInput>
            );
        }}
    </FormDataConsumer>
);

const ItemSelector = () => (
    <ReferenceInput source="itemId" reference="itens" label="Item" perPage={100}>
        <AutocompleteInput optionText={(record) => record?.titulo?.nome ?? record?.id} validate={required()} />
    </ReferenceInput>
);

const buildDefaultValues = (record) => {
    if (!record) return {};

    const tipo = (record?.cliente?.tipo ?? 'SOCIO').toLowerCase();
    const baseValues = {
        dtLocacao: record?.dtLocacao,
        dtDevolucaoPrevista: record?.dtDevolucaoPrevista,
        dtDevolucaoEfetiva: record?.dtDevolucaoEfetiva,
        valorCobrado: record?.valorCobrado,
        multaCobrada: record?.multaCobrada,
        itemId: record?.item?.id,
        clienteTipo: tipo,
    };

    if (tipo === 'dependente') {
        baseValues.dependenteId = record?.cliente?.id;
    } else {
        baseValues.socioId = record?.cliente?.id;
    }

    return baseValues;
};

export const LocacaoEdit = (props) => {
    const notify = useNotify();

    const handleError = (error) => {
        const message = buildErrorMessage(error, 'Erro ao atualizar locação.');
        notify(message, { type: 'error' });
    };

    return (
        <Edit {...props} transform={sanitizePayload} onError={handleError}>
            <SimpleForm defaultValues={buildDefaultValues}>
                <DateInput source="dtDevolucaoPrevista" label="Devolução prevista" validate={required()} />
                <NumberInput source="valorCobrado" label="Valor cobrado" validate={required()} min={0} />
            </SimpleForm>
        </Edit>
    );
};

