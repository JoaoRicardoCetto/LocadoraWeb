import { DateInput, Edit, SimpleForm, TextInput, required } from 'react-admin';

export const ItemEdit = () => (
    <Edit>
        <SimpleForm>
            <TextInput source="id" validate={required()} />
            <TextInput source="numSerie" />
            <TextInput source="tipo" validate={required()} />
            <DateInput source="dataAquisicao" validate={required()} />
            <DateInput source="preco" validate={required()} />
            <TextInput source="titulo" />
        </SimpleForm>
    </Edit>
);