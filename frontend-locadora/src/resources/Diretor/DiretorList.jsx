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
    DeleteWithConfirmButton, 
} from 'react-admin';

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
            <DeleteWithConfirmButton />
            <ShowButton />
        </Datagrid>
    </List>
);