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
  useNotify
} from 'react-admin';

const validateRequired = required();

export const TituloCreate = (props) => {
  const notify = useNotify();
  const handleError = (error) => {
    let message = 'Erro ao criar título.';
    if (error?.body) {
      try {
        const data = JSON.parse(error.body);
        if (data.message) message = data.message;
        if (data.erros && Array.isArray(data.erros) && data.erros.length > 0) {
          message += '\n' + data.erros.map(e => `${e.campo}: ${e.erro}`).join('\n');
        }
      } catch {}
    }
    notify(message, { type: 'error' });
  };
  return (
    <Create {...props} onError={handleError}>
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
};