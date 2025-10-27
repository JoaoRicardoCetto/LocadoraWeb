import { DataTable, List } from 'react-admin';

export const DiretorList = () => (
    <List>
        <DataTable>
            <DataTable.Col source="nome" />
            <DataTable.Col source="titulos" />
        </DataTable>
    </List>
);