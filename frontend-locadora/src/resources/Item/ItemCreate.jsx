import {
    Create,
    SimpleForm,
    TextInput,
    DateInput,
    required,
    ReferenceInput,
    AutocompleteInput,
    NumberInput
} from 'react-admin';

const validateRequired = required();

import { useNotify } from 'react-admin';

export const ItemCreate = (props) => {
    const notify = useNotify();
    const handleError = (error) => {
        // Tenta extrair mensagem do backend
        let message = 'Erro ao criar item.';
        if (error?.body) {
            try {
                const data = JSON.parse(error.body);
                if (data.message) message = data.message;
                if (data.erros && Array.isArray(data.erros) && data.erros.length > 0) {
                    message += '\n' + data.erros.map(e => `${e.campo}: ${e.erro}`).join('\n');
                }
            } catch {}
        }
        notify(message, { type: 'error' });
    };
    return (
        <Create {...props} onError={handleError}>
            <SimpleForm>
                <NumberInput source="numSerie" validate={validateRequired} />
                <TextInput source="tipo" validate={validateRequired} />
                <DateInput source="dataAquisicao" validate={validateRequired} />
            </SimpleForm>
        </Create>
    );
};