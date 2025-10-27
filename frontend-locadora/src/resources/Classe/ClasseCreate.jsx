import { Create, SimpleForm, TextInput, required, NumberInput, DateInput } from 'react-admin';

export const ClasseCreate = (props) => (
    <Create {...props}>
        <SimpleForm>
            <TextInput source="nome" validate={required()} />
            <NumberInput source="valor" validate={required()} />
            <DateInput source="prazoDevolucao" validate={required()} />
            <TextInput source="titulos" />
        </SimpleForm>
    </Create>
);