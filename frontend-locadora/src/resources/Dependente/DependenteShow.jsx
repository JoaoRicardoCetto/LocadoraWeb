import { Show, SimpleShowLayout, TextField, DateField, ReferenceField, BooleanField } from 'react-admin';

export const DependenteShow = () => (
    <Show>
        <SimpleShowLayout>
            <TextField source="id" />
            <TextField source="numInscricao" label="Inscrição" />
            <TextField source="nome" />
            <DateField source="dtNascimento" />
            <TextField source="sexo" />
            <BooleanField source="estahAtivo" label="Está ativo" />
            <ReferenceField label="Sócio" source="socio.id" reference="socios">
                <TextField source="nome" />
            </ReferenceField>
        </SimpleShowLayout>
    </Show>
);


