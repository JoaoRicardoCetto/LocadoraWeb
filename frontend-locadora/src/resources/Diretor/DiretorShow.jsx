import { Show, SimpleShowLayout, TextField } from 'react-admin';

export const DiretorShow = () => (
    <Show>
        <SimpleShowLayout>
            <TextField source="id" />
            <TextField source="nome" />
            <TextField source="titulos" />
        </SimpleShowLayout>
    </Show>
);