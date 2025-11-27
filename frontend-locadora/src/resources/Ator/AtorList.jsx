import {
    List,
    Datagrid,
    TextField,
    ArrayField,
    SingleFieldList,
    ChipField,
    EditButton,
    DeleteButton,
    ShowButton,
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
            
            <EditButton label=""/>
            <DeleteButton label=""/>
            <ShowButton label=""/>
        </Datagrid>
    </List>
);