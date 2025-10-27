import { List, Datagrid, TextField, DateField, EditButton } from 'react-admin';

export const ItemList = () => (
    <List>
        <Datagrid rowClick="show">
            <TextField source="numSerie" />
            <TextField source="tipo" />
            <DateField source="dataAquisicao" />
            {/* Mostrar título aninhado (nome) */}
            <TextField label="Título" source="titulo.nome" />
            <EditButton />
        </Datagrid>
    </List>
);