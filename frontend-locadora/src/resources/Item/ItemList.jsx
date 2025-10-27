import { DataTable, DateField, List } from 'react-admin';

export const ItemList = () => (
    <List>
        <DataTable>
            <DataTable.Col source="numSerie" />
            <DataTable.Col source="tipo" />
            <DataTable.Col source="dataAquisicao">
                <DateField source="dataAquisicao" />
            </DataTable.Col>
            <DataTable.Col source="titulo" />
        </DataTable>
    </List>
);