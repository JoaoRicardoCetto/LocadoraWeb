import { DateField, Show, SimpleShowLayout, TextField, NumberField } from 'react-admin';

export const ItemShow = () => (
    <Show>
        <SimpleShowLayout>
            <TextField source="id" />
            <TextField source="numSerie" />
            <TextField source="tipo" />
            <DateField source="dataAquisicao" />
            {/* Item não possui preco na entidade; removido */}
            {/* Mostrar título aninhado */}
            <TextField label="Titulo" source="titulo.nome" />
        </SimpleShowLayout>
    </Show>
);