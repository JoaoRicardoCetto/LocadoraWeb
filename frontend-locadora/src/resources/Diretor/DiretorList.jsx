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

            <EditButton label=""/>
            <
                DeleteWithConfirmButton 
                label="" 
                confirmTitle="Tem certeza que deseja excluír diretor?"
                confirmContent="Atenção! Esta ação é irreversível e removerá permanentemente o sócio e seus dependentes do sistema."
            />
            <ShowButton label=""/>

        </Datagrid>
    </List>
);