import {
  List,
  Datagrid,
  TextField,
  DateField,
  ArrayField,
  SingleFieldList,
  ChipField,
} from 'react-admin';

export const TituloList = () => (
  <List>
    <Datagrid rowClick="show">
      <TextField source="nome" />
      <DateField source="ano" showTime={false} />
      <TextField source="sinopse" />
      <TextField source="categoria" />

      {/* objetos aninhados */}
      <TextField label="Diretor" source="diretor.nome" />
      <TextField label="Classe" source="classe.nome" />

      <ArrayField label="Atores" source="atores">
        <SingleFieldList>
          <ChipField source="nome" />
        </SingleFieldList>
      </ArrayField>
      <ArrayField label="Itens" source="itens">
        <SingleFieldList>
          <ChipField source="numSerie" />
        </SingleFieldList>
      </ArrayField>
    </Datagrid>
  </List>
);
