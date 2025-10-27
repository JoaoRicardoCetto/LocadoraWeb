import { Create, SimpleForm, TextInput, required } from 'react-admin';

export const DiretorCreate = (props) => (
    <Create {...props}>
        <SimpleForm>
            <TextInput source="nome" validate={required()} />
            <TextInput source="titulos" />
        </SimpleForm>
    </Create>
);