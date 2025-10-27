import { DateField, Show, SimpleShowLayout, TextField, ArrayField, SingleFieldList, ChipField } from 'react-admin';

export const TituloShow = () => (
    <Show>
        <SimpleShowLayout>
            <TextField source="id" />
            <TextField source="nome" />
            <DateField source="ano" />
            <TextField source="sinopse" />
            <TextField source="categoria" />
            
            <TextField label="Diretor" source="diretor.nome" />
        
            <TextField label="Classe" source="classe.nome" />
            
            <ArrayField label="Atores" source="atores">
                <SingleFieldList>
                    <ChipField source="nome" />
                </SingleFieldList>
            </ArrayField>

        </SimpleShowLayout>
    </Show>
);