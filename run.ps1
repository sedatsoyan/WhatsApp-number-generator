Write-Host "Java project compile..."

# 'bin' klasörünü oluştur
New-Item -ItemType Directory -Force -Path "bin" | Out-Null

# Java kaynak dosyalarını derle (src klasöründen bin klasörüne)
javac -d bin .\src\*.java

if ($LASTEXITCODE -eq 0) {
    Write-Host "project running..."
    java -cp bin Main
} else {
    Write-Host "compile error..."
}
