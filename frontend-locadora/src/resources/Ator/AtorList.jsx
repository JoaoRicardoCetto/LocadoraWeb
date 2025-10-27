import {
    List,
    Datagrid,
    TextField,
    ArrayField,
    SingleFieldList,
    ChipField,
} from 'react-admin';

export const AtorList = () => (
    <List>
        <Datagrid rowClick="show">
            <TextField source="nome" />

            <ArrayField label="Títulos" source="titulos">
                <SingleFieldList>
                    <ChipField source="nome" />
                </SingleFieldList>
            </ArrayField>
        </Datagrid>
    </List>
);