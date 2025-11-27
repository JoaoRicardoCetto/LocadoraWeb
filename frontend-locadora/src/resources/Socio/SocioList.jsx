import { 
    List, 
    Datagrid, 
    TextField, 
    DateField, 
    ArrayField, 
    SingleFieldList, 
    ChipField, 
    EditButton,
    DeleteButton,
    ShowButton,
    DeleteWithConfirmButton,
    FunctionField
 } from 'react-admin';

import ToggleAtivoButton from '../ToggleAtivoButton';
import { Box } from '@mui/material'; // Para lidar com responsividade

export const SocioList = () => (
    <List>
        <Box sx={{ overflowX: 'auto' }}> 

            <Datagrid rowClick="" size="small">
                <TextField source="numInscricao" label="Inscrição" />
                <TextField source="nome" />
                <DateField source="dtNascimento" />
                <TextField source="sexo" />
                <TextField source="cpf" />
                <TextField source="endereco" />
                <TextField source="telefone" />
                <ArrayField label="Dependentes" source="dependentes">
                    <SingleFieldList>
                        <ChipField source="nome" />
                    </SingleFieldList>
                </ArrayField>

                <FunctionField label="Atividade" render={() => (
                    <ToggleAtivoButton resourceOverride="socios" activeFieldOverride="estahAtivo" method="PATCH" />
                    )} 
                />

                <EditButton label=""/>
                <
                    DeleteWithConfirmButton 
                    label="" confirmTitle="Tem certeza que deseja excluír sócio?"
                    confirmContent="Atenção! Esta ação é irreversível e removerá permanentemente o sócio e seus dependentes do sistema."
                />
                <ShowButton label=""/>

            </Datagrid>
        </Box>
    </List>
);

