import { List, Datagrid, TextField, DateField, ReferenceField, EditButton } from 'react-admin';

export const ItemList = () => (
    <List>
        <Datagrid rowClick="show">
            <TextField source="numSerie" />
            <TextField source="tipo" />
            <DateField source="dataAquisicao" />
            <ReferenceField source="titulo" reference="titulo" link="show">
                <TextField source="nome" />
            </ReferenceField>
            <EditButton />
        </Datagrid>
    </List>
);