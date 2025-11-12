import { Create, SimpleForm, TextInput, DateInput, required } from 'react-admin';

const validateRequired = required();

export const SocioCreate = (props) => (
    <Create {...props}>
        <SimpleForm>
            <TextInput source="numInscricao" label="Inscrição" validate={validateRequired} />
            <TextInput source="nome" validate={validateRequired} />
            <DateInput source="dtNascimento" validate={validateRequired} />
            <TextInput source="sexo" validate={validateRequired} />
            <TextInput source="cpf" validate={validateRequired} />
            <TextInput source="endereco" />
            <TextInput source="telefone" />
        </SimpleForm>
    </Create>
);

