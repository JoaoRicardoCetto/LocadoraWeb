import { List, Datagrid, TextField, DateField, ArrayField, SingleFieldList, ChipField } from 'react-admin';

export const SocioList = () => (
    <List>
        <Datagrid rowClick="show">
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
        </Datagrid>
    </List>
);

