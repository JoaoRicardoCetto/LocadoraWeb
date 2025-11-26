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

export const DependenteList = () => (
    <List>
        <Datagrid rowClick="">
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

            <EditButton />
            <DeleteWithConfirmButton />
            <ShowButton />
            
        </Datagrid>
    </List>
);

