import { useState } from 'react';
import {
  useNotify,
  useRedirect,
} from 'react-admin';
import {
  Box,
  TextField as MuiTextField,
  Button,
  Paper,
  Typography,
  InputAdornment,
  CircularProgress,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Chip,
  FormControl,
  FormLabel,
  RadioGroup,
  FormControlLabel,
  Radio,
} from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import VisibilityIcon from '@mui/icons-material/Visibility';

const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080';

export const PesquisaTitulo = () => {
  const [termoBusca, setTermoBusca] = useState('');
  const [tipoBusca, setTipoBusca] = useState('todos');
  const [resultados, setResultados] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const notify = useNotify();
  const redirect = useRedirect();

  const realizarBusca = async () => {
    if (!termoBusca.trim()) {
      notify('Por favor, digite um termo de busca', { type: 'warning' });
      return;
    }

    setLoading(true);
    setError(null);

    try {
      const url = `${API_URL}/titulos/buscar?termo=${encodeURIComponent(termoBusca)}&tipo=${encodeURIComponent(tipoBusca)}`;
      const response = await fetch(url);
      if (response.ok) {
        const dados = await response.json();
        setResultados(dados);
        if (dados.length === 0) {
          notify('Nenhum resultado encontrado', { type: 'info' });
        }
      } else {
        throw new Error('Erro ao buscar títulos');
      }
    } catch (err) {
      setError(err.message);
      notify('Erro ao realizar busca', { type: 'error' });
    } finally {
      setLoading(false);
    }
  };

  const handleKeyPress = (e) => {
    if (e.key === 'Enter') {
      realizarBusca();
    }
  };

  const formatarData = (data) => {
    if (!data) return '-';
    try {
      const date = new Date(data);
      return date.toLocaleDateString('pt-BR');
    } catch {
      return data;
    }
  };

  const formatarValor = (valor) => {
    if (valor == null || valor === undefined) return '-';
    return new Intl.NumberFormat('pt-BR', {
      style: 'currency',
      currency: 'BRL'
    }).format(valor);
  };

  const calcularFitasDisponiveis = (itens) => {
    if (!itens || itens.length === 0) return 0;
    // Por enquanto, retornamos o total de itens
    // Em uma implementação completa, deveríamos verificar quais itens não estão locados
    // (sem locação com dtDevolucaoEfetiva == null)
    return itens.length;
  };

  const handleVerDetalhes = (id) => {
    redirect('show', 'titulos', id);
  };

  const handleVerDiretor = (e, diretorId) => {
    e.stopPropagation();
    if (diretorId) {
      redirect('show', 'diretores', diretorId);
    }
  };

  const handleVerClasse = (e, classeId) => {
    e.stopPropagation();
    if (classeId) {
      redirect('show', 'classes', classeId);
    }
  };

  const handleVerAtor = (e, atorId) => {
    e.stopPropagation();
    if (atorId) {
      redirect('show', 'atores', atorId);
    }
  };

  const handleVerItem = (e, itemId) => {
    e.stopPropagation();
    if (itemId) {
      redirect('show', 'itens', itemId);
    }
  };

  return (
    <Box sx={{ padding: 2 }}>
      <Paper sx={{ padding: 3, marginBottom: 3 }}>
        <Typography variant="h5" gutterBottom>
          Pesquisa de Títulos
        </Typography>
        <Typography variant="body2" color="text.secondary" sx={{ marginBottom: 3 }}>
          Pesquise por nome do Título, Categoria, nome da Classe ou nome do Autor
        </Typography>
        
        <FormControl component="fieldset" sx={{ marginBottom: 2 }}>
          <FormLabel component="legend">Buscar por:</FormLabel>
          <RadioGroup
            row
            value={tipoBusca}
            onChange={(e) => setTipoBusca(e.target.value)}
          >
            <FormControlLabel value="todos" control={<Radio />} label="Todos" />
            <FormControlLabel value="titulo" control={<Radio />} label="Nome do Título" />
            <FormControlLabel value="categoria" control={<Radio />} label="Categoria" />
            <FormControlLabel value="ator" control={<Radio />} label="Nome do Ator" />
            <FormControlLabel value="classe" control={<Radio />} label="Nome da Classe" />
          </RadioGroup>
        </FormControl>

        <Box sx={{ display: 'flex', gap: 2, alignItems: 'center' }}>
          <MuiTextField
            fullWidth
            label="Termo de busca"
            value={termoBusca}
            onChange={(e) => setTermoBusca(e.target.value)}
            onKeyPress={handleKeyPress}
            placeholder={
              tipoBusca === 'titulo' ? 'Digite o nome do título...' :
              tipoBusca === 'categoria' ? 'Digite a categoria...' :
              tipoBusca === 'ator' ? 'Digite o nome do ator...' :
              tipoBusca === 'classe' ? 'Digite o nome da classe...' :
              'Digite o nome do título, categoria, classe ou autor...'
            }
            disabled={loading}
            InputProps={{
              startAdornment: (
                <InputAdornment position="start">
                  <SearchIcon />
                </InputAdornment>
              ),
            }}
          />
          <Button
            variant="contained"
            onClick={realizarBusca}
            startIcon={loading ? <CircularProgress size={20} color="inherit" /> : <SearchIcon />}
            sx={{ minWidth: 150 }}
            disabled={loading}
          >
            {loading ? 'Buscando...' : 'Buscar'}
          </Button>
        </Box>
      </Paper>

      {error && (
        <Paper sx={{ padding: 2, marginBottom: 2, backgroundColor: 'error.light' }}>
          <Typography variant="body2" color="error">
            Erro: {error}
          </Typography>
        </Paper>
      )}

      {resultados.length > 0 && (
        <Paper>
          <Typography variant="h6" sx={{ padding: 2 }}>
            Resultados da busca ({resultados.length}) - Buscando por: {
              tipoBusca === 'titulo' ? 'Nome do Título' :
              tipoBusca === 'categoria' ? 'Categoria' :
              tipoBusca === 'ator' ? 'Nome do Ator' :
              tipoBusca === 'classe' ? 'Nome da Classe' :
              'Todos os campos'
            }
          </Typography>
          <TableContainer>
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell><strong>Título</strong></TableCell>
                  <TableCell><strong>Ano</strong></TableCell>
                  <TableCell><strong>Sinopse</strong></TableCell>
                  <TableCell><strong>Categoria</strong></TableCell>
                  <TableCell><strong>Diretor</strong></TableCell>
                  <TableCell><strong>Classe</strong></TableCell>
                  <TableCell><strong>Valor Locação</strong></TableCell>
                  <TableCell><strong>Atores</strong></TableCell>
                  <TableCell><strong>Fitas Disponíveis</strong></TableCell>
                  <TableCell><strong>Ações</strong></TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {resultados.map((titulo) => (
                  <TableRow key={titulo.id} hover>
                    <TableCell>{titulo.nome || '-'}</TableCell>
                    <TableCell>{formatarData(titulo.ano)}</TableCell>
                    <TableCell sx={{ maxWidth: 200, overflow: 'hidden', textOverflow: 'ellipsis' }}>
                      {titulo.sinopse || '-'}
                    </TableCell>
                    <TableCell>{titulo.categoria || '-'}</TableCell>
                    <TableCell>
                      {titulo.diretor?.nome ? (
                        <Typography
                          component="span"
                          onClick={(e) => handleVerDiretor(e, titulo.diretor?.id)}
                          sx={{
                            color: 'primary.main',
                            cursor: 'pointer',
                            textDecoration: 'underline',
                            '&:hover': {
                              color: 'primary.dark',
                            },
                          }}
                        >
                          {titulo.diretor.nome}
                        </Typography>
                      ) : '-'}
                    </TableCell>
                    <TableCell>
                      {titulo.classe?.nome ? (
                        <Typography
                          component="span"
                          onClick={(e) => handleVerClasse(e, titulo.classe?.id)}
                          sx={{
                            color: 'primary.main',
                            cursor: 'pointer',
                            textDecoration: 'underline',
                            '&:hover': {
                              color: 'primary.dark',
                            },
                          }}
                        >
                          {titulo.classe.nome}
                        </Typography>
                      ) : '-'}
                    </TableCell>
                    <TableCell>
                      {titulo.classe?.valor != null ? formatarValor(titulo.classe.valor) : '-'}
                    </TableCell>
                    <TableCell>
                      {titulo.atores && titulo.atores.length > 0 ? (
                        <Box sx={{ display: 'flex', flexWrap: 'wrap', gap: 0.5 }}>
                          {titulo.atores.map((ator, idx) => (
                            <Chip
                              key={ator.id || idx}
                              label={ator.nome}
                              size="small"
                              onClick={(e) => handleVerAtor(e, ator.id)}
                              sx={{
                                cursor: 'pointer',
                                '&:hover': {
                                  backgroundColor: 'primary.light',
                                  color: 'primary.contrastText',
                                },
                              }}
                            />
                          ))}
                        </Box>
                      ) : '-'}
                    </TableCell>
                    <TableCell>
                      <Typography variant="body2">
                        {calcularFitasDisponiveis(titulo.itens)} / {titulo.itens?.length || 0}
                      </Typography>
                    </TableCell>
                    <TableCell>
                      <Button
                        size="small"
                        startIcon={<VisibilityIcon />}
                        onClick={() => handleVerDetalhes(titulo.id)}
                      >
                        Ver
                      </Button>
                    </TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        </Paper>
      )}

      {termoBusca && resultados.length === 0 && !loading && (
        <Paper sx={{ padding: 3, textAlign: 'center' }}>
          <Typography variant="body1" color="text.secondary">
            Nenhum resultado encontrado para "{termoBusca}" buscando por: {
              tipoBusca === 'titulo' ? 'Nome do Título' :
              tipoBusca === 'categoria' ? 'Categoria' :
              tipoBusca === 'ator' ? 'Nome do Ator' :
              tipoBusca === 'classe' ? 'Nome da Classe' :
              'Todos os campos'
            }
          </Typography>
        </Paper>
      )}
    </Box>
  );
};

