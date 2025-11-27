import { 
    List, 
    Datagrid, 
    TextField, 
    DateField, 
    ReferenceField, 
    EditButton,
    DeleteWithConfirmButton,
    ShowButton,
    FunctionField 
} from 'react-admin';

import ToggleAtivoButton from '../ToggleAtivoButton';
import { Box } from '@mui/material'; // Para lidar com responsividade

export const DependenteList = () => (
    <List>
        <Box sx={{ overflowX: 'auto' }}> 
            <Datagrid rowClick="" size="small">
                <TextField source="numInscricao" label="Inscrição" />
                <TextField source="nome" />
                <DateField source="dtNascimento" />
                <TextField source="sexo" />
                <ReferenceField label="Sócio" source="socio.id" reference="socios">
                    <TextField source="nome" />
                </ReferenceField>

                <FunctionField label="Atividade" render={() => (
                    <ToggleAtivoButton resourceOverride="dependentes" activeFieldOverride="estaAtivo" method="PATCH" />
                    )} 
                />

                <EditButton label=""/>

                <
                    DeleteWithConfirmButton 
                    label="" confirmTitle="Tem certeza que deseja excluír dependente?"
                    confirmContent="Atenção! Esta ação é irreversível e removerá permanentemente o dependente do sistema."
                />

                <ShowButton label=""/>
                
            </Datagrid>
        </Box>
    </List>
);

