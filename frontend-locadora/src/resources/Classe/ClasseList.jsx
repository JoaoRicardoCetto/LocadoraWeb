import { List, Datagrid, TextField, DateField, NumberField, EditButton, ArrayField, SingleFieldList, ChipField } from 'react-admin';

export const ClasseList = () => (
    <List>
        <Datagrid rowClick="show">
            <TextField source="nome" />
            <NumberField source="valor" />
            <NumberField source="prazoDevolucao" />
            <ArrayField label="Títulos" source="titulos">
                <SingleFieldList>
                    <ChipField source="nome" />
                </SingleFieldList>
            </ArrayField>
        </Datagrid>
    </List>
);