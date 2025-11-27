import { 
    List, 
    Datagrid, 
    TextField, 
    DateField, 
    FunctionField,
    EditButton,
    DeleteButton,
    ShowButton,
} from 'react-admin';

import { Box } from '@mui/material'; // Para lidar com responsividade

export const ItemList = () => (
    <List>
        <Box sx={{ overflowX: 'auto' }}> 
            <Datagrid rowClick="show" size="small">
                <FunctionField
                    label="Nº Série"
                    render={record => `${record.numSerie}`}
                />
                <TextField source="tipo" label="Tipo" />
                <DateField source="dataAquisicao" label="Data Aquisição" />
                <TextField label="Titulo" source="titulo.nome" link="show" />

                <EditButton label=""/>
                <DeleteButton label=""/>
                <ShowButton label=""/>

            </Datagrid>
        </Box>
    </List>
);