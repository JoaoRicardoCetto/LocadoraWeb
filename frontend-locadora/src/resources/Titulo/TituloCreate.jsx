import {
  Create,
  SimpleForm,
  TextInput,
  DateInput,
  required,
  ReferenceInput,
  SelectInput,
  ReferenceArrayInput,
  SelectArrayInput,
} from 'react-admin';

const validateRequired = required();

export const TituloCreate = (props) => (
  <Create {...props}>
    <SimpleForm>
      <TextInput source="nome" validate={validateRequired} />
      <DateInput source="ano" validate={validateRequired} />
      <TextInput source="sinopse" validate={validateRequired} />
      <TextInput source="categoria" validate={validateRequired} />

      <ReferenceInput source="diretorId" reference="diretores" allowEmpty>
        <SelectInput optionText="nome" />
      </ReferenceInput>

      <ReferenceInput source="classeId" reference="classes" allowEmpty>
        <SelectInput optionText="nome" />
      </ReferenceInput>

      <ReferenceArrayInput source="atoresIds" reference="atores" allowEmpty>
        <SelectArrayInput optionText="nome" />
      </ReferenceArrayInput>
    </SimpleForm>
  </Create>
);