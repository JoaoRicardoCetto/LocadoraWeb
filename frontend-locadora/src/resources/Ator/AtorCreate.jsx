import {
  Create,
  SimpleForm,
  TextInput,
  required,
  ReferenceArrayInput,
  AutocompleteArrayInput,
} from 'react-admin';

const validateRequired = required();

export const AtorCreate = (props) => {
  const transform = (data) => ({
    ...data,
    titulos: data.titulos || [],
  });

  return (
    <Create {...props} transform={transform}>
      <SimpleForm>
        <TextInput source="nome" validate={validateRequired} />
      </SimpleForm>
    </Create>
  );
};
