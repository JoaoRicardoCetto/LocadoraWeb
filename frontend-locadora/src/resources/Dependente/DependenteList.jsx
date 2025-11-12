import { List, Datagrid, TextField, DateField, ReferenceField } from 'react-admin';

export const DependenteList = () => (
    <List>
        <Datagrid rowClick="show">
            <TextField source="numInscricao" label="Inscrição" />
            <TextField source="nome" />
            <DateField source="dtNascimento" />
            <TextField source="sexo" />
            <ReferenceField label="Sócio" source="socio.id" reference="socios">
                <TextField source="nome" />
            </ReferenceField>
        </Datagrid>
    </List>
);

