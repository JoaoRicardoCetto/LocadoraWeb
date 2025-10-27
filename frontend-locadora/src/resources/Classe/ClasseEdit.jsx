import { DateInput, Edit, NumberInput, SimpleForm, TextInput, required } from 'react-admin';

export const ClasseEdit = () => (
    <Edit>
        <SimpleForm>
            <TextInput source="nome" validate={required()} />
            <NumberInput source="valor" validate={required()} />
            <DateInput source="prazoDevolucao" validate={required()} />
            <TextInput source="titulos" />
        </SimpleForm>
    </Edit>
);