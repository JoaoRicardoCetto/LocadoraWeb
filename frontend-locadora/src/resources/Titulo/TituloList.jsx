import {
  List,
  Datagrid,
  TextField,
  DateField,
  ArrayField,
  SingleFieldList,
  ChipField,
  ReferenceManyField
} from 'react-admin';

export const TituloList = () => (
  <List>
    <Datagrid rowClick="show">
      <TextField source="nome" />
      <DateField source="ano" showTime={false} />
      <TextField source="sinopse" />
      <TextField source="categoria" />

      <TextField label="Diretor" source="diretor.nome" link="show" />

      <TextField label="Classe" source="classe.nome" link="show" />

      <ArrayField label="Atores" source="atores" link="show">
        <SingleFieldList>
          <ChipField source="nome" />
        </SingleFieldList>
      </ArrayField>

      <ArrayField label="Itens" source="itens" link="show">
        <SingleFieldList>
          <ChipField source="numSerie" />
        </SingleFieldList>
      </ArrayField>
    </Datagrid>
  </List>
);
