import { 
    List, 
    Datagrid, 
    TextField, 
    NumberField, 
    ArrayField, 
    SingleFieldList, 
    ChipField, 
    EditButton,
    DeleteButton,
    ShowButton, 
} from 'react-admin';

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

            <EditButton />
            <DeleteButton />
            <ShowButton />

        </Datagrid>
    </List>
);