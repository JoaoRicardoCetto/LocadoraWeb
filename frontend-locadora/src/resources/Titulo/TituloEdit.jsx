import {
    Edit,
    SimpleForm,
    TextInput,
    DateInput,
    required,
    ReferenceInput,
    SelectInput,
    ReferenceArrayInput,
    AutocompleteArrayInput,
} from 'react-admin';

const validateRequired = required();

export const TituloEdit = (props) => {
    // Sua função transform é perfeita para o DTO de Request
    const transform = (formData) => {
        const payload = { ...formData };

        // Lógica de Diretor e Classe (manter)
        // ...
        if (payload.diretor && typeof payload.diretor === 'object') {
             payload.diretorId = payload.diretor.id ?? payload.diretor;
        } 
        else if (payload.diretor) {
             payload.diretorId = payload.diretor;
        }
        delete payload.diretor; 

        if (payload.classe && typeof payload.classe === 'object') {
             payload.classeId = payload.classe.id ?? payload.classe;
        }
        else if (payload.classe) {
             payload.classeId = payload.classe;
        }
        delete payload.classe;

        // Lógica de Atores (manter)
        if (payload.atores) {
            payload.atoresIds = payload.atores;
            delete payload.atores;
        }

        // Remove props auxiliares/undefined
        Object.keys(payload).forEach((k) => {
            if (payload[k] === undefined) 
                delete payload[k];
        });

        return payload;
    };

    // Função de formato para o ReferenceArrayInput
    // Ela garante que o valor do objeto (Set<AtorResponseDto>) seja transformado em um Array de Strings (UUIDs)
    const formatAtores = (value) => {
        if (Array.isArray(value)) {
            return value.map(item => (typeof item === 'object' && item !== null ? String(item.id) : String(item)));
        }
        return [];
    };

    return (
        <Edit {...props} transform={transform}>
            <SimpleForm>
                <TextInput source="nome" validate={validateRequired} />
                <DateInput source="ano" validate={validateRequired} />
                <TextInput source="sinopse" validate={validateRequired} />
                <TextInput source="categoria" validate={validateRequired} />

                {/* Diretor e Classe (Mantidos, mas atentos ao source.id) */}
                <ReferenceInput
                    source="diretor.id" 
                    reference="diretores"
                    allowEmpty
                >
                    <SelectInput optionText="nome" />
                </ReferenceInput>

                <ReferenceInput
                    source="classe.id"
                    reference="classes"
                    allowEmpty
                >
                    <SelectInput optionText="nome" />
                </ReferenceInput>

                {/* --- CAMPO ATORES CORRIGIDO E OTIMIZADO --- */}
                <ReferenceArrayInput
                    source="atores" // source="atores" é o campo que contém o array de objetos
                    reference="atores"
                    allowEmpty
                    // Aplica a formatação para extrair apenas o ID como string
                    format={formatAtores}
                    // Adicione este filtro vazio; ele garante que o AutocompleteArrayInput
                    // dispare a busca pelos IDs iniciais assim que o formulário é carregado.
                    filter={{}}
                >
                    <AutocompleteArrayInput optionText="nome" />
                </ReferenceArrayInput>
            </SimpleForm>
        </Edit>
    );
};