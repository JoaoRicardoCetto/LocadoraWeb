import { DateField, Show, SimpleShowLayout, TextField, NumberField, ReferenceField } from 'react-admin';

export const ItemShow = () => (
    <Show>
        <SimpleShowLayout>
            <TextField source="id" />
            <TextField source="numSerie" />
            <TextField source="tipo" />
            <DateField source="dataAquisicao" />
            <NumberField source="preco" />
            <ReferenceField source="titulo" reference="titulo" link="show">
                <TextField source="nome" />
            </ReferenceField>
        </SimpleShowLayout>
    </Show>
);