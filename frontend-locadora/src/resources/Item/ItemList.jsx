import { 
    List, 
    Datagrid, 
    TextField, 
    DateField, 
    EditButton,
    ReferenceField,
    FunctionField
} from 'react-admin';

export const ItemList = () => (
    <List>
        <Datagrid rowClick="show">
            <FunctionField
                label="Nº Série"
                render={record => `${record.numSerie}`}
            />
            <TextField source="tipo" label="Tipo" />
            <DateField source="dataAquisicao" label="Data Aquisição" />
            <TextField label="Titulo" source="titulo.nome" link="show" />
        </Datagrid>
    </List>
);