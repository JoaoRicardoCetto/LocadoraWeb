import { Create, SimpleForm, TextInput, DateInput, required } from 'react-admin';

export const ItemCreate = (props) => (
    <Create {...props}>
        <SimpleForm>
            <TextInput source="numSerie" />
            <TextInput source="tipo" validate={required}/>
            <DateInput source="dataAquisicao" validate={required()} />
            <DateInput source="preco" validate={required()} />
            <TextInput source="titulo" />
        </SimpleForm>
    </Create>
);