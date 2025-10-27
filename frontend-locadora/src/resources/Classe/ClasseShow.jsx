import { DateField, NumberField, Show, SimpleShowLayout, TextField } from 'react-admin';

export const ClasseShow = () => (
    <Show>
        <SimpleShowLayout>
            <TextField source="id" />
            <TextField source="nome" />
            <NumberField source="valor" />
            <DateField source="prazoDevolucao" />
            <TextField source="titulos" />
        </SimpleShowLayout>
    </Show>
);