import { Create, SimpleForm, TextInput, DateInput, required, ReferenceInput, AutocompleteInput } from 'react-admin';

const validateRequired = required();

export const DependenteCreate = (props) => (
    <Create {...props}>
        <SimpleForm>
            <TextInput source="numInscricao" label="Inscrição" validate={validateRequired} />
            <TextInput source="nome" validate={validateRequired} />
            <DateInput source="dtNascimento" validate={validateRequired} />
            <TextInput source="sexo" validate={validateRequired} />

            <ReferenceInput source="socioId" reference="socios">
                <AutocompleteInput optionText="nome" validate={validateRequired} />
            </ReferenceInput>
        </SimpleForm>
    </Create>
);

