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

export const ItemCreate = (props) => {
    // No transform required if ReferenceInput source is the id field (tituloId)
    return (
        <Create {...props}>
            <SimpleForm>
                <NumberInput source="numSerie" validate={validateRequired} />
                <TextInput source="tipo" validate={validateRequired} />
                <DateInput source="dataAquisicao" validate={validateRequired} />

                {/* Seleciona o título por ID (campo esperado no backend: tituloId) */}
                <ReferenceInput source="tituloId" reference="titulos" allowEmpty>
                    <AutocompleteInput optionText="nome" />
                </ReferenceInput>
            </SimpleForm>
        </Create>
    );
};