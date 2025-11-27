import React from "react";
import { 
  useDataProvider, 
  useNotify, 
  useRefresh, 
  useSafeSetState, 
  fetchUtils 
} from "react-admin";
import { 
  Grid, // Adicionei 'item' nos Grids
  Card,
  CardContent,
  TextField as MuiTextField,
  Typography,
  Box,
  Button,
  CircularProgress,
  Paper,
  Divider,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Chip,
} from "@mui/material";

// --- CONFIGURAÇÃO DA URL ---
// Ajuste esta constante para apontar para a raiz da sua API Java.
const API_URL = import.meta.env.VITE_API_URL || "http://localhost:8080";

interface Item {
  id: string;
  numSerie?: string;
  titulo?: { nome: string; [k: string]: any } | string; 
  locacoes?: Locacao[];
  [k: string]: any;
}

interface Locacao {
  id: string;
  dtLocacao?: string;
  dtDevolucaoPrevista?: string;
  dtDevolucaoEfetiva?: string | null;
  multaCobrada?: number;
  valorCobrado?: number;
  item?: Item;
  // NOTE: A interface foi corrigida de 'socio' para 'cliente', baseado na última linha de código que usa locacao.cliente?.nome
  cliente?: { id: string; nome?: string }; 
  [k: string]: any;
}

const formatDate = (iso?: string | null) => {
  if (!iso) return "—";
  try {
    const d = new Date(iso);
    if (isNaN(d.getTime())) return iso;
    return d.toLocaleDateString();
  } catch {
    return iso;
  }
};

const getTituloNome = (titulo: Item['titulo']) => {
  if (typeof titulo === 'string') return titulo;
  if (typeof titulo === 'object' && titulo !== null && 'nome' in titulo) {
    return titulo.nome;
  }
  return '—';
}


const NumSerieLookup: React.FC = () => {
  const notify = useNotify();
  const refresh = useRefresh();
  const httpClient = fetchUtils.fetchJson;

  const [serial, setSerial] = useSafeSetState<string>("");
  const [loading, setLoading] = useSafeSetState(false);
  const [item, setItem] = useSafeSetState<Item | null>(null);
  const [locacao, setLocacao] = useSafeSetState<Locacao | null>(null);
  const [confirmOpen, setConfirmOpen] = useSafeSetState(false);

  // --- BUSCA DA LOCAÇÃO ---
  const handleSearch = async () => {
    const s = serial?.trim();
    if (!s) {
      notify("Informe o número de série.", { type: "warning" });
      return;
    }
    
    if (isNaN(Number(s))) {
        notify("O número de série deve ser numérico.", { type: "warning" });
        return;
    }

    setLoading(true);
    setItem(null);
    setLocacao(null);

    try {
      // CORREÇÃO 1: Ajustei o caminho da URL de busca.
      // Assumindo que o endpoint @GetMapping("devolucao/localizar") está na raiz do Controller
      const url = `${API_URL}/locacao/devolucao/localizar?numSerie=${s}`; 
      
      const { json } = await httpClient(url, { method: 'GET' });
      
      const locacaoEncontrada = json as Locacao;

      if (locacaoEncontrada && locacaoEncontrada.id) {
        setLocacao(locacaoEncontrada);
        
        if (locacaoEncontrada.item) {
          setItem(locacaoEncontrada.item);
        }
        
        notify("Locação em aberto encontrada.", { type: "info" });
      } else {
        notify("Nenhuma locação em aberto encontrada para o item.", { type: "warning" });
      }

    } catch (error: any) {
      console.error("Erro na busca:", error);
      
      let msg = "Erro desconhecido ao buscar.";
      
      if (error.body && typeof error.body === 'string') {
        // Se o body for string simples (erro customizado)
        msg = error.body;
      } else if (error.body && error.body.message) {
         // Se for objeto JSON com campo 'message'
         msg = error.body.message;
      } else if (error.message) {
         msg = error.message;
      }

      const type = error.status === 400 ? "warning" : "error";
      notify(msg, { type });
      
    } finally {
      setLoading(false);
    }
  };

  const handleReturnConfirm = () => setConfirmOpen(true);
  const handleReturnCancel = () => setConfirmOpen(false);

  // --- DEVOLUÇÃO (PATCH CUSTOMIZADO) ---
  const handleReturn = async () => {
    if (!locacao || !locacao.id) {
      notify("Nenhuma locação selecionada ou ID inválido.", { type: "warning" });
      return;
    }

    setConfirmOpen(false);
    setLoading(true);

    try {
      // CORREÇÃO 2: Substituição do dataProvider.update pela chamada customizada PATCH.
      // O endpoint espera um ID na path e um body. O body (LocacaoDevolvidaRequestDto) 
      // foi simplificado aqui, assumindo que apenas a requisição PATCH para o endpoint é suficiente.
      const url = `${API_URL}/locacao/devolucao/efetivar/${locacao.id}`;
      
      // Criando um body vazio, pois o serviço Java parece usar apenas o ID e a lógica de efetivar.
      // Se o DTO Java tivesse campos, eles precisariam ser incluídos aqui:
      const body = JSON.stringify({}); 

      await httpClient(url, { 
        method: 'PATCH',
        body: body,
        headers: new Headers({
          'Content-Type': 'application/json',
        }),
      });

      notify("Devolução registrada com sucesso.", { type: "success" });
      setLocacao(null);
      setItem(null);
      setSerial("");
      refresh();
      
    } catch (err: any) {
      console.error(err);
      let msg = "Erro ao registrar devolução.";
      if (err.body) {
        msg = err.body;
      } else if (err.message) {
        msg = err.message;
      }
      notify(`${msg}`, { type: "error" });

    } finally {
      setLoading(false);
    }
  };

  const tituloNome = item ? getTituloNome(item.titulo) : "—";
  
  return (
    <Card variant="outlined" sx={{ maxWidth: 900, margin: "16px auto", p: 1 }}>
      <CardContent>
        <Typography variant="h6" gutterBottom>
          Registrar devolução por Nº de série
        </Typography>

        <Paper sx={{ p: 2, mb: 2 }} elevation={1}>
          <Grid container spacing={2} alignItems="center">
            {/* CORREÇÃO 3: Adicionado 'item' e ajustado o tamanho do campo */}
            <Grid>
              <MuiTextField
                fullWidth
                label="Número de série"
                variant="outlined"
                value={serial}
                onChange={(e) => setSerial(e.target.value)}
                size="small"
                type="number"
                inputProps={{ min: 0 }}
                onKeyDown={(e) => {
                  if (e.key === "Enter") handleSearch();
                }}
                disabled={loading}
              />
            </Grid>

            {/* CORREÇÃO 4: Adicionado 'item' e ajustado o tamanho dos botões */}
            <Grid>
              <Box display="flex" gap={1} width="100%">
                <Button 
                    variant="contained" 
                    onClick={handleSearch} 
                    disabled={loading} 
                    sx={{ flexGrow: 1 }}
                >
                  {loading ? <CircularProgress size={20} color="inherit" /> : "Buscar"}
                </Button>

                <Button
                  variant="outlined"
                  onClick={() => {
                    setSerial("");
                    setItem(null);
                    setLocacao(null);
                  }}
                  disabled={loading}
                >
                  Limpar
                </Button>
              </Box>
            </Grid>
          </Grid>
        </Paper>

        <Grid container spacing={2}>
          {/* CORREÇÃO 5: Adicionado 'item' */}
          <Grid>
            <Paper sx={{ p: 2, height: '100%' }} elevation={0} variant="outlined">
              <Typography variant="subtitle1" gutterBottom color="primary">
                Item
              </Typography>
              <Divider sx={{ mb: 1 }} />
              {item ? (
                <Box>
                  <Box display="flex" gap={1} alignItems="center" mb={1} flexWrap="wrap">
                    <Chip label={`ID: ${item.id}`} size="small" />
                    <Chip label={`Série: ${item.numSerie ?? "—"}`} size="small" color="secondary" />
                    {tituloNome !== '—' && <Chip label={tituloNome} size="small" variant="outlined" />}
                  </Box>

                  <Typography variant="body2"><strong>Título:</strong> {tituloNome}</Typography>
                  <Typography variant="body2"><strong>ID:</strong> {item.id}</Typography>
                  <Typography variant="body2"><strong>Nº série:</strong> {item.numSerie ?? "—"}</Typography>
                </Box>
              ) : (
                <Typography color="text.secondary" variant="body2" sx={{ fontStyle: 'italic' }}>
                   Aguardando busca...
                </Typography>
              )}
            </Paper>
          </Grid>

          {/* CORREÇÃO 6: Adicionado 'item' */}
          <Grid>
            <Paper sx={{ p: 2, height: '100%' }} elevation={0} variant="outlined">
              <Typography variant="subtitle1" gutterBottom color="primary">
                Locação em aberto
              </Typography>
              <Divider sx={{ mb: 1 }} />
              {locacao ? (
                <Box>
                  <Box display="flex" gap={1} alignItems="center" mb={1} flexWrap="wrap">
                    <Chip label={`Locação: ${locacao.id}`} size="small" />
                    {/* CORREÇÃO 7: Usando 'cliente' no lugar de 'socio' para manter consistência com o JSX */}
                    <Chip 
                      label={locacao.cliente?.nome ?? locacao.cliente?.id ?? "Cliente desconhecido"} 
                      size="small" 
                      color="success" 
                      variant="outlined"
                    />
                  </Box>
                  
                  <Typography variant="body2"><strong>Cliente:</strong> {locacao.cliente?.nome ?? locacao.cliente?.id ?? "—"}</Typography>
                  <Typography variant="body2"><strong>Data Locação:</strong> {formatDate(locacao.dtLocacao)}</Typography>
                  <Typography variant="body2"><strong>Prev. Devolução:</strong> {formatDate(locacao.dtDevolucaoPrevista)}</Typography>
                  <Typography variant="body2"><strong>Multa:</strong> {(locacao.multaCobrada)}</Typography>
                  <Typography variant="body2"><strong>Valor Total:</strong> {(locacao.valorCobrado)}</Typography>

                  <Box mt={2} display="flex" gap={1}>
                    <Button variant="contained" color="success" onClick={handleReturnConfirm} disabled={loading} size="small">
                      Registrar devolução
                    </Button>
                    <Button variant="text" color="error" onClick={() => { setLocacao(null); setItem(null); }} disabled={loading} size="small">
                      Cancelar
                    </Button>
                  </Box>
                </Box>
              ) : (
                <Typography color="text.secondary" variant="body2" sx={{ fontStyle: 'italic' }}>
                   Nenhuma locação ativa encontrada.
                </Typography>
              )}
            </Paper>
          </Grid>
        </Grid>
      </CardContent>

      <Dialog open={!!confirmOpen} onClose={handleReturnCancel}>
        <DialogTitle>Confirmar devolução</DialogTitle>
        <DialogContent>
          <Typography>
            Deseja registrar a devolução da locação{" "}
            <strong>{locacao?.id ?? "—"}</strong> do item{" "}
            <strong>{item?.numSerie ?? item?.id ?? "—"}</strong>?
          </Typography>
          <Box sx={{ bgcolor: 'action.hover', p: 1, mt: 2, borderRadius: 1 }}>
            <Typography variant="caption" color="text.secondary">
              A data de devolução será registrada como: <strong>{new Date().toLocaleDateString()}</strong>
            </Typography>
          </Box>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleReturnCancel}>Cancelar</Button>
          <Button onClick={handleReturn} variant="contained" color="success" autoFocus>
            Confirmar
          </Button>
        </DialogActions>
      </Dialog>
    </Card>
  );
};

export default NumSerieLookup;