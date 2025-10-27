import {
    Edit,
    SimpleForm,
    TextInput,
    DateInput,
    required,
    ReferenceInput,
    AutocompleteInput,
    NumberInput
} from 'react-admin';

const validateRequired = required();

export const ItemEdit = (props) => {
    // transform to convert nested titulo object into tituloId when present
    const transform = (formData) => {
        const payload = { ...formData };
        if (payload.titulo && typeof payload.titulo === 'object') {
            payload.tituloId = payload.titulo.id ?? payload.titulo;
        } else if (payload.tituloId) {
            payload.tituloId = payload.tituloId;
        }
        delete payload.titulo;

        Object.keys(payload).forEach((k) => {
            if (payload[k] === undefined) delete payload[k];
        });

        return payload;
    };

    return (
        <Edit {...props} transform={transform}>
            <SimpleForm>
                <NumberInput source="numSerie" validate={validateRequired} />
                <TextInput source="tipo" validate={validateRequired} />
                <DateInput source="dataAquisicao" validate={validateRequired} />

                <ReferenceInput source="tituloId" reference="titulos" allowEmpty>
                    <AutocompleteInput optionText="nome" />
                </ReferenceInput>
            </SimpleForm>
        </Edit>
    );
};