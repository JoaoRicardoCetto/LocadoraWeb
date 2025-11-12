import { DateField, Show, SimpleShowLayout, TextField, ReferenceField } from 'react-admin';

export const ItemShow = () => (
    <Show>
        <SimpleShowLayout>
            <TextField source="id" />
            <TextField source="numSerie" />
            <TextField source="tipo" />
            <DateField source="dataAquisicao" />

            <ReferenceField label="Título" source="titulo.id" reference="titulos">
                <TextField source="nome" />
            </ReferenceField>

        </SimpleShowLayout>
    </Show>
);