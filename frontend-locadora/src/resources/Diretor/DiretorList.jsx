import { List, Datagrid, TextField, EditButton, ArrayField, SingleFieldList, ChipField } from 'react-admin';

export const DiretorList = () => (
    <List>
        <Datagrid rowClick="show">
            <TextField source="nome" />
            <ArrayField label="Títulos" source="titulos">
                <SingleFieldList>
                    <ChipField source="nome" />
                </SingleFieldList>
            </ArrayField>
            <EditButton />
        </Datagrid>
    </List>
);