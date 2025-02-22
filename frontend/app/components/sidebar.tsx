"use client"

import { useState } from "react"
import Link from "next/link"
import { cn } from "@/lib/utils"
import { Button } from "@/components/ui/button"
import { ScrollArea } from "@/components/ui/scroll-area"
import { Sheet, SheetContent, SheetTrigger } from "@/components/ui/sheet"
import { Menu, Users, Calendar, Settings, BarChart, ChevronLeft, ChevronRight } from "lucide-react"
import Image from "next/image";

const sidebarItems = [
    { name: "Tableau de bord", icon: BarChart, href: "/" },
    { name: "Élèves", icon: Users, href: "/students" },
    { name: "Séances", icon: Calendar, href: "/sessions" },
    { name: "Paramètres", icon: Settings, href: "/settings" },
]

interface SidebarProps extends React.HTMLAttributes<HTMLDivElement> {
    isOpen: boolean
    onToggle: () => void
}

export function Sidebar({ className, isOpen, onToggle }: SidebarProps) {
    const [isMobileOpen, setIsMobileOpen] = useState(false)

    return (
        <>
            <Sheet open={isMobileOpen} onOpenChange={setIsMobileOpen}>
                <SheetTrigger asChild>
                    <Button variant="outline" size="icon" className="md:hidden fixed left-4 top-4 z-40">
                        <Menu className="h-4 w-4" />
                    </Button>
                </SheetTrigger>
                <SheetContent side="left" className="w-[240px] sm:w-[300px]">
                    <div className="flex flex-col h-full">
                        <div className="w-2/4">
                            <Image src="/COLOR_calisthenx.svg" alt="CalisthenX Logo" width={25} height={25} className="w-full h-auto" />
                        </div>
                        <div className="space-y-4 py-4">
                            <h2 className="mb-2 px-4 text-lg font-semibold tracking-tight">CalisthenX</h2>
                            <nav className="space-y-1">
                                {sidebarItems.map((item) => (
                                    <Link
                                        key={item.name}
                                        href={item.href}
                                        className="flex items-center px-4 py-2 text-sm font-medium rounded-md hover:bg-accent hover:text-accent-foreground"
                                        onClick={() => setIsMobileOpen(false)}
                                    >
                                        <item.icon className="mr-2 h-4 w-4" />
                                        {item.name}
                                    </Link>
                                ))}
                            </nav>
                        </div>
                    </div>
                </SheetContent>
            </Sheet>
            <div
                className={cn(
                    "fixed inset-y-0 left-0 z-30 w-64 transform transition-transform duration-300 ease-in-out",
                    isOpen ? "translate-x-0" : "-translate-x-full",
                    "md:relative md:translate-x-0",
                    className,
                )}
            >
                <div className="h-full bg-background border-r">
                    <div className="w-2/4">
                        <Image src="/COLOR_calisthenx.svg" alt="CalisthenX Logo" width={25} height={25} className="w-full h-auto" priority={true} />
                    </div>
                    <div className="flex items-center justify-between p-4">
                        <h2 className="text-lg font-semibold tracking-tight">CalisthenX</h2>
                        <Button variant="ghost" size="icon" onClick={onToggle} className="md:hidden">
                            <ChevronLeft className="h-4 w-4" />
                        </Button>
                    </div>
                    <ScrollArea className="h-[calc(100vh-5rem)] pb-10">
                        <nav className="space-y-1 p-2">
                            {sidebarItems.map((item) => (
                                <Link
                                    key={item.name}
                                    href={item.href}
                                    className="flex items-center px-4 py-2 text-sm font-medium rounded-md hover:bg-accent hover:text-accent-foreground"
                                >
                                    <item.icon className="mr-2 h-4 w-4" />
                                    {item.name}
                                </Link>
                            ))}
                        </nav>
                    </ScrollArea>
                </div>
            </div>
            <Button
                variant="outline"
                size="icon"
                onClick={onToggle}
                className={cn("fixed bottom-4 z-40 md:hidden", isOpen ? "left-64" : "left-4")}
            >
                {isOpen ? <ChevronLeft className="h-4 w-4" /> : <ChevronRight className="h-4 w-4" />}
            </Button>
        </>
    )
}

