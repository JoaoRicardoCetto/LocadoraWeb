import { Edit, SimpleForm, TextInput, required } from 'react-admin';

export const DiretorEdit = () => (
    <Edit>
        <SimpleForm>
            <TextInput source="nome" validate={required()} />
            <TextInput source="titulos" />
        </SimpleForm>
    </Edit>
);