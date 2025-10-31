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
    return (
        <Create {...props}>
            <SimpleForm>
                <NumberInput source="numSerie" validate={validateRequired} />
                <TextInput source="tipo" validate={validateRequired} />
                <DateInput source="dataAquisicao" validate={validateRequired} />
            </SimpleForm>
        </Create>
    );
};