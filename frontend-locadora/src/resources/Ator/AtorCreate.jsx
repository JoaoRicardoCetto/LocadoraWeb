import {
  Create,
  SimpleForm,
  TextInput,
  required,
  ReferenceArrayInput,
  AutocompleteArrayInput,
  useNotify
} from 'react-admin';

const validateRequired = required();

export const AtorCreate = (props) => {
  const notify = useNotify();
  const transform = (data) => ({
    ...data,
    titulos: data.titulos || [],
  });
  const handleError = (error) => {
    let message = 'Erro ao criar ator.';
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
    <Create {...props} transform={transform} onError={handleError}>
      <SimpleForm>
        <TextInput source="nome" validate={validateRequired} />
      </SimpleForm>
    </Create>
  );
};
