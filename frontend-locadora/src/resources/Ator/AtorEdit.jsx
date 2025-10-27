import { Edit, SimpleForm, TextInput, required } from 'react-admin';

export const AtorEdit = () => (
    <Edit>
        <SimpleForm>
            <TextInput source="nome" validate={required()}/>
            <TextInput source="titulos" />
        </SimpleForm>
    </Edit>
);