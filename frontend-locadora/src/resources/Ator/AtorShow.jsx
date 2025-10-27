import { Show, SimpleShowLayout, TextField } from 'react-admin';

export const AtorShow = () => (
    <Show>
        <SimpleShowLayout>
            <TextField source="id" />
            <TextField source="nome" />
            <TextField source="titulos" />
        </SimpleShowLayout>
    </Show>
);