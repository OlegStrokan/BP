FROM mcr.microsoft.com/dotnet/aspnet:7.0 AS base
WORKDIR /app
EXPOSE 80
EXPOSE 443

FROM mcr.microsoft.com/dotnet/sdk:7.0 AS build
WORKDIR /src
COPY ["myDpdApp/myDpdApp.csproj", "myDpdApp/"]
RUN dotnet restore "myDpdApp/myDpdApp.csproj"
COPY . .
WORKDIR "/src/myDpdApp"
RUN dotnet build "myDpdApp.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "myDpdApp.csproj" -c Release -o /app/publish

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "myDpdApp.dll"]
