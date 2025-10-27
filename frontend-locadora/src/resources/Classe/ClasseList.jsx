import { DataTable, DateField, List } from 'react-admin';

export const ClasseList = () => (
    <List>
        <DataTable>
            <DataTable.Col source="nome" />
            <DataTable.NumberCol source="valor" />
            <DataTable.Col source="prazoDevolucao">
                <DateField source="prazoDevolucao" />
            </DataTable.Col>
            <DataTable.Col source="titulos" />
        </DataTable>
    </List>
);