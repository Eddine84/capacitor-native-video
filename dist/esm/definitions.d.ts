export interface NativeVideoPlugin {
    echo(options: {
        value: string;
    }): Promise<{
        value: string;
    }>;
}
