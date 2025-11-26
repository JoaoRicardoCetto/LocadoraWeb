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

export const SocioList = () => (
    <List>
        <Datagrid rowClick="">
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

            <EditButton />
            <DeleteWithConfirmButton />
            <ShowButton />

        </Datagrid>
    </List>
);

