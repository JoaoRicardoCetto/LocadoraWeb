import { DateField, Show, SimpleShowLayout, TextField } from 'react-admin';

export const ItemShow = () => (
    <Show>
        <SimpleShowLayout>
            <TextField source="id" />
            <TextField source="numSerie" />
            <TextField source="tipo" />
            <DateField source="dataAquisicao" />
            <DateField source="preco" />
            <TextField source="titulo" />
        </SimpleShowLayout>
    </Show>
);